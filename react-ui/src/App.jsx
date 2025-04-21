import Login from "./components/auth/Login"
import CustomerDashboard from "./components/customer/CustomerDashboard"
import Post from "./components/playground/posts"
import Todo from "./components/playground/todo"


function App() { //app is a component and a component must have a return 

  return (
    <div>
      {
        //<Login />
        //<CustomerDashboard />
        //<Post />
        <Todo />
      }
    </div>
  )
}

export default App
