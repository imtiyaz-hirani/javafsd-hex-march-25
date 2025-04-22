import axios from "axios"
import { useEffect, useState } from "react"

function StudentList() {
    const [students, setStudents] = useState([])

    useEffect(() => {
        const getAllStudents = async () => {
            try {
                const response = await axios.get('http://localhost:8081/api/student/all?page=0&size=5')
                //console.log(response)
                setStudents(response.data)
            }
            catch (err) {
                console.log(err)
            }
        }

        getAllStudents()
    }, [])
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
                            students.map((s, index) => (
                                <tr key={index}>
                                    <th scope="row"> {s.id}</th>
                                    <td>{s.name}</td>
                                    <td>{s.email}</td>
                                    <td>{s.age}</td>
                                    <td>
                                        <button className="btn btn-danger btn-sm">Delete</button>
                                        &nbsp;&nbsp;&nbsp;
                                        <button className="btn btn-info btn-sm">Update</button>
                                    </td>
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
                                <li className="page-item"><a className="page-link" href="#">Previous</a></li>
                                <li className="page-item"><a className="page-link" href="#">1</a></li>
                                <li className="page-item"><a className="page-link" href="#">2</a></li>
                                <li className="page-item"><a className="page-link" href="#">3</a></li>
                                <li className="page-item"><a className="page-link" href="#">Next</a></li>
                            </ul>
                        </nav>
                    </div>

                </div>

            </div>
        </div>
    )
}

export default StudentList