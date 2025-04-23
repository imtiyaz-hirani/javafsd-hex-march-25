import { useEffect } from "react";
import CustomerNavbar from "./navbar";
import axios from "axios";
import { useState } from "react";

function CustomerDashboard() {

    const [categories, setCategories] = useState([])

    useEffect(() => {
        const getAllCategories = async () => {
            try {
                const response = await axios.get('http://localhost:8081/api/category/all');
                setCategories(response.data)
            }
            catch (err) {
                console.log(err)
            }
        }
        getAllCategories();
    }, [])
    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-lg-12">
                    <CustomerNavbar />
                </div>
            </div>

            <div className="row">
                <div className="col-lg-12">
                    <h2>Featured Categories </h2>
                    <div className="row" >
                        {
                            categories.map((c, index) => (
                                <div className="col-sm-2" key={index}>
                                    <div className="card">
                                        <div className="card-body">
                                            {c.name}
                                        </div>
                                    </div>
                                </div>
                            ))
                        }
                    </div>
                </div>
            </div>
        </div>
    )
}

export default CustomerDashboard; 