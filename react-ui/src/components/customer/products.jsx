import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router";
import CustomerNavbar from "./navbar";

function CustomerProducts() {

    const [products, setProducts] = useState([])
    const { cid } = useParams('cid')
    const { cname } = useParams('cname')

    useEffect(() => {
        const getProducts = async () => {
            try {
                const response = await axios.get(`http://localhost:8081/api/product/category/${cid}?page=0&size=5`)
                setProducts(response.data)
            }
            catch (err) {

            }
        }

        getProducts();
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
                    <h2>Category: {cname} </h2>
                </div>
                <div className="row" >
                    {
                        products.map((p, index) => (
                            <div className="col-sm-4" key={index}>
                                <div className="card" style={{ width: '18rem' }}>
                                    <div className="card-body">
                                        <h5 className="card-title">{p.title}</h5>
                                        <p className="card-text">{p.shortDescription}</p>
                                        <a href="#" className="btn btn-primary">View Details</a>
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

export default CustomerProducts;