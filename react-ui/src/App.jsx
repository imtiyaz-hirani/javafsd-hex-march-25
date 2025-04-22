import Login from "./components/auth/Login"
import CustomerDashboard from "./components/customer/CustomerDashboard"
import Post from "./components/playground/posts"
import StudentAdd from "./components/playground/student-add"
import StudentList from "./components/playground/student-list"
import Todo from "./components/playground/todo"


function App() { //app is a component and a component must have a return 

  return (
    <div>
      {
        //<Login />
        //<CustomerDashboard />
        //<Post />
        //<Todo />
        //<StudentAdd />
        <StudentList />
      }
    </div>
  )
}

export default App
