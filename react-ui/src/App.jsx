import { Route, Routes } from "react-router"
import Login from "./components/auth/Login"
import CustomerDashboard from "./components/customer/CustomerDashboard"
import CustomerProducts from "./components/customer/products"
import CustomerSignUp from "./components/customer/signup"
import VendorDashboard from "./components/vendor/VendorDashboard"

function App() { //app is a component and a component must have a return 

  return (
    <Routes>
      <Route index path="" element={<Login />} />
      <Route path="/customer/signup" element={<CustomerSignUp />} />
      <Route path="customer" element={<CustomerDashboard />} />
      <Route path="vendor" element={<VendorDashboard />} />

      <Route path="product/:cid/:cname" element={<CustomerProducts />} />
    </Routes>
  )
}

export default App
