import Login from "./components/auth/Login"
import CustomerDashboard from "./components/customer/CustomerDashboard"
import Post from "./components/playground/posts"

function App() { //app is a component and a component must have a return 

  return (
    <div>
      {
        //<Login />
        //<CustomerDashboard />
        <Post />
      }
    </div>
  )
}

export default App
