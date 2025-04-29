import { Route, Routes } from "react-router"
import Login from "./components/auth/Login"
import CustomerDashboard from "./components/customer/CustomerDashboard"
import CustomerProducts from "./components/customer/products"
import CustomerSignUp from "./components/customer/signup"
import VendorDashboard from "./components/vendor/VendorDashboard"
import UserList from "./components/playground/users"
import { useDispatch } from "react-redux"
import { useEffect } from "react"
import fetchAlbums from "./store/actions/albumActions"
import AlbumList from "./components/playground/albums"
import ChartDashboard from "./components/playground/dashboard"

function App() { //app is a component and a component must have a return 

  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(fetchAlbums())
  }, [])

  return (
    <Routes>
      <Route index path="" element={<Login />} />
      <Route path="customer/signup" element={<CustomerSignUp />} />
      <Route path="customer" element={<CustomerDashboard />} />
      <Route path="vendor" element={<VendorDashboard />} />
      <Route path="users" element={<UserList />} />
      <Route path="albums" element={<AlbumList />} />
      <Route path="chart-dashboard" element={<ChartDashboard />} />
      <Route path="product/:cid/:cname" element={<CustomerProducts />} />
    </Routes>
  )
}

export default App
