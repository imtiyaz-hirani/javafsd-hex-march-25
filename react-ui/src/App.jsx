import { Route, Routes } from "react-router"
import Login from "./components/auth/Login"
import CustomerDashboard from "./components/customer/CustomerDashboard"

function App() { //app is a component and a component must have a return 

  return (
    <Routes>
      <Route index path="" element={<Login />} />
      <Route path="customer" element={<CustomerDashboard />} />
    </Routes>
  )
}

export default App
