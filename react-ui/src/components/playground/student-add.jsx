import axios from "axios";
import { useState } from "react";

function StudentAdd() {
    const [name, setName] = useState(null);
    const [email, setEmail] = useState(null);
    const [age, setAge] = useState(null);

    const addStudent = async ($event) => {
        $event.preventDefault();
        console.log(name)
        console.log(email)
        console.log(age)

        let obj = {
            'name': name,
            'email': email,
            'age': age
        }

        let response = await axios.post('http://localhost:8081/api/student/add', obj);
        console.log(response)
    }
    return (
        <div>
            <h3>Add Student Details </h3>
            <form onSubmit={($event) => addStudent($event)}>
                <div className="mb-4">
                    <label>Enter Student Name: </label>
                    <input type="text"
                        onChange={($event) => { setName($event.target.value) }} />
                </div>
                <div className="mb-4">
                    <label>Enter Student Email: </label>
                    <input type="text"
                        onChange={($event) => { setEmail($event.target.value) }} ></input>
                </div>
                <div className="mb-4">
                    <label>Enter Student Age: </label>
                    <input type="number"
                        onChange={($event) => { setAge($event.target.value) }} ></input>
                </div>
                <div className="mb-4">

                    <input type="submit"
                        value="Add Student Details"
                        className="btn btn-primary" />
                </div>

            </form>
        </div>
    )
}

export default StudentAdd;