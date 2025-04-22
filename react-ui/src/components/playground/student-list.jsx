import axios from "axios"
import { useEffect, useState } from "react"

function StudentList() {
    const [students, setStudents] = useState([])
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(5);
    const [totalPages, setTotalPages] = useState(0);
    const [name, setName] = useState(null);
    const [age, setAge] = useState(null);
    const getAllStudents = async () => {
        try {
            const response
                = await axios.get(`http://localhost:8081/api/student/all?page=${page}&size=${size}`)
            //console.log(response)
            setStudents(response.data.list)
            setTotalPages(response.data.totalPages)
        }
        catch (err) {
            console.log(err)
        }
    }

    useEffect(() => {

        getAllStudents()
    }, [page])
    /** [] : indicates that useEffect will be called only once.
     * But in this case whenever the value of page changes, we want react to call useEffect. 
     */

    const deleteStudents = (sid) => {
        /** Call the API and delete the record */
        try {
            const resp = axios.delete(`http://localhost:8081/api/student/delete/${sid}`);
            let temp = [...students];
            temp = temp.filter(s => s.id !== sid)
            setStudents(temp)
        }
        catch (err) {
            console.log(err)
        }
        //getAllStudents() -- dont call API unless its very necessary. 
    }

    const updateStudent = async ($e, sid) => {
        $e.preventDefault();
        try {
            const response = await axios.put('http://localhost:8081/api/student/update/' + sid, {
                'name': name,
                'age': age
            });

            //update the record from my array 
            let sObj = students.filter(s => s.id === sid);
            sObj.name = name
            sObj.age = age
            sObj.id = response.data.id
            sObj.email = response.data.email
            //delete the record u want to update 
            let temp = [...students.filter(s => s.id !== sid)]
            //add updated record in temp 
            temp.push(sObj)

            //set this temp to students state 
            setStudents(temp)
        }
        catch (err) {
            console.log(err)
        }


    }
    return (
        <div>
            <h1>All Students</h1>
            <div className="container">
                <table className="table">
                    <thead>
                        <tr>
                            <th scope="col">#ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Age</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            students.sort((a, b) => a.id - b.id).map((s, index) => (
                                <tr key={index}>
                                    <th scope="row"> {s.id}</th>
                                    <td>{s.name}</td>
                                    <td>{s.email}</td>
                                    <td>{s.age}</td>
                                    <td>
                                        <button className="btn btn-danger btn-sm" onClick={() => { deleteStudents(s.id) }}>Delete</button>
                                        &nbsp;&nbsp;&nbsp;
                                        <button className="btn btn-info btn-sm"
                                            data-bs-toggle="modal" data-bs-target={`#update-${s.id}`}>Update</button>
                                    </td>

                                    {/** Modal Box*/}
                                    <div class="modal fade" id={`update-${s.id}`} tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div className="modal-dialog">
                                            <div className="modal-content">
                                                <div className="modal-header">
                                                    <h5 className="modal-title" id="exampleModalLabel">Update Student Record</h5>
                                                    <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div className="modal-body">
                                                    <form onSubmit={($e) => updateStudent($e, s.id)}>

                                                        <div className="mb-4">
                                                            <label>Enter Student Name: </label>
                                                            <input type="text" placeholder={s.name} className="form-control"
                                                                onChange={($event) => { setName($event.target.value) }} />
                                                        </div>
                                                        <div className="mb-4">
                                                            <label>Enter Student Age: </label>
                                                            <input type="number" placeholder={s.age} className="form-control"
                                                                onChange={($event) => { setAge($event.target.value) }} ></input>
                                                        </div>
                                                        <div className="mb-4">

                                                            <input type="submit"
                                                                value="Edit Student Details"
                                                                className="btn btn-primary"
                                                                data-bs-dismiss="modal" />
                                                        </div>

                                                    </form>
                                                </div>
                                                <div className="modal-footer">
                                                    <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </tr>
                            ))
                        }


                    </tbody>
                </table>
                <div className="row">
                    <div className="col-md-4">

                    </div>
                    <div className="col-md-6">
                        <nav aria-label="Page navigation example">
                            <ul className="pagination">
                                <li className="page-item"><a className="page-link" href="#"
                                    onClick={() => { page === 0 ? setPage(0) : setPage(page - 1) }}>Previous</a></li>


                                <li className="page-item"><a className="page-link" href="#"
                                    onClick={() => { page === totalPages - 1 ? setPage(page) : setPage(page + 1) }} >Next</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>

            </div>




        </div>
    )
}

export default StudentList