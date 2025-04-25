import axios from "axios";
import { useEffect, useState } from "react";

function VendorDashboard() {

    const [categories, setCategories] = useState([])
    const [warehouses, setWarehouses] = useState([])

    const [title, setTitle] = useState(null);
    const [price, setPrice] = useState(null);
    const [shortDescription, setshortDescription] = useState(null);
    const [description, setdescription] = useState(null);
    const [cid, setcid] = useState(null);
    const [wid, setwid] = useState(null);

    const [product, setProduct] = useState(null);

    useEffect(() => {
        const getCategory = async () => {
            try {
                const resp = await axios.get('http://localhost:8081/api/category/all')
                setCategories(resp.data)
            }
            catch (err) {
                console.log(err)
            }
        }

        const getWarehouse = async () => {
            try {
                const resp = await axios.get('http://localhost:8081/api/warehouse/all')
                setWarehouses(resp.data)
            }
            catch (err) {
                console.log(err)
            }
        }

        getCategory();
        getWarehouse();
    }, [])

    const add = async (e) => {
        //prevent the form getting refreshed 
        e.preventDefault();
        // console.log(title)
        // console.log(price)
        // console.log(cid)
        // console.log(wid)
        // console.log(shortDescription)
        // console.log(description)

        let body = {
            "title": title,
            "shortDescription": shortDescription,
            "description": description,
            "imageUrl": "",
            "price": price
        }
        let token = localStorage.getItem('token')

        let headers = {
            headers: {
                "Authorization": `Bearer ${token}`  //token goes here but not getting detected in backend
            }
        }
        try {
            const resp = await axios.post(`http://localhost:8081/api/product/add/${cid}/${wid}`,
                body,
                headers
            )
            setProduct(resp.data)
            console.log(product)
        }
        catch (err) {
            console.log(err)
        }
    }
    return (
        <div>
            <h1>Vendor dashboard</h1>

            <div className="container">
                <div className="card">
                    <div className="card-header">
                        <h3>Add product details</h3>
                    </div>
                    <div className="card-body">
                        <form className="row g-3" onSubmit={(e) => { add(e) }}>
                            <div className="col-md-6">
                                <label className="form-label">Title</label>
                                <input type="text" className="form-control"
                                    onChange={(e) => { setTitle(e.target.value) }} />
                            </div>
                            <div className="col-md-6">
                                <label className="form-label">Price</label>
                                <input type="number" className="form-control"
                                    onChange={(e) => { setPrice(e.target.value) }} />
                            </div>
                            <div className="col-12">
                                <label className="form-label">Short Description</label>
                                <input type="text" className="form-control" placeholder="enter product highlights"
                                    onChange={(e) => { setshortDescription(e.target.value) }} />
                            </div>
                            <div className="col-12">
                                <label for="inputAddress2" className="form-label">Full Description</label>
                                <textarea className="form-control" rows={10} cols={50}
                                    onChange={(e) => { setdescription(e.target.value) }}></textarea>
                            </div>
                            <div className="col-md-6">
                                <label className="form-label">Select Category</label>
                                <select className="form-select" onChange={(e) => { setcid(e.target.value) }}>
                                    <option selected>Choose...</option>
                                    {
                                        categories.map((c, index) => (
                                            <option key={index} value={c.id}> {c.name}</option>
                                        ))
                                    }

                                </select>
                            </div>
                            <div className="col-md-6">
                                <label className="form-label">Select Warehouse</label>
                                <select className="form-select" onChange={(e) => { setwid(e.target.value) }}>
                                    <option selected>Choose...</option>
                                    {
                                        warehouses.map((w, index) => (
                                            <option key={index} value={w.id}>{w.name}</option>
                                        ))
                                    }

                                </select>
                            </div>


                            <div className="col-12">
                                <button type="submit" className="btn btn-primary">Add Product</button>
                            </div>
                        </form>

                    </div>

                </div>

            </div>
        </div>
    )
}

export default VendorDashboard; 