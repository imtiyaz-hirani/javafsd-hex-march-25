import axios from "axios";
import { useEffect, useState } from "react"

function Todo() {

    const [todos, setTodo] = useState([])
    const url = 'https://jsonplaceholder.typicode.com/todos';

    useEffect(() => {
        const getTodos = () => {
            fetch(url)
                .then(resp => resp.json())
                .then(data => setTodo(data))
                .catch(err => {
                    console.log(err)
                })
        }

        const getTodosWithAxios = async () => {
            try {
                let response = await axios.get(url);
                //console.log(response)
                setTodo(response.data)
            }
            catch (err) {
                console.log(err)
            }
            finally {

            }
        }

        //getTodos();
        getTodosWithAxios()
    }, []);

    const deleteTodo = (todoId) => {
        //console.log(todoId)  -- check and comment 

        //make api call to delete -- skip this time 

        /** 
         *  delete this todo that the user has clicked from the aaaay
         * to do this, we must use filter method. 
         */

        let tempArry = [...todos];
        /* this step creates a new array tempArry(pos:500X) and takes all elements 
        from todos array and saves at new location. 

        After this, tempArry will hold all todos. 

        Y  is this necessary? todos is a state that we cannot modify. hence we use temp array modify it, 
        and using setter give new array to state todo. 
        */

        tempArry = tempArry.filter(t => t.id !== todoId); //tempArray has 1 less element 
        /** 
         * if this condition 't.id !== todoId' satisfies and gives true then that element stays. 
         * so in short, only element that the user the clicked will leave and others will stay. 
         */

        setTodo(tempArry)
    }
    return (
        <div>
            <h1>All Todos</h1>
            <div className="container">
                <div className="row">
                    {
                        todos.map((todo, index) => (
                            <div className="col-sm-4 mb-4" key={index}>
                                <div className="card">
                                    <div className="card-body">
                                        {todo.id} <br />
                                        {todo.userId} <br />
                                        {todo.title} <br /><br />
                                        <button className="btn btn-danger" onClick={() => deleteTodo(todo.id)}>Delete Todo</button>
                                    </div>
                                </div>
                            </div>
                        ))
                    }

                </div>
            </div>


        </div>

    )
}

export default Todo;