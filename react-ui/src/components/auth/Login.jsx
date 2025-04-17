import { useState } from "react";

function Login(){
    const [username,setUsername] = useState(null);
    const [password,setPassword] = useState(null);
    const [msg,setMsg] = useState(null);

    const login = ()=>{
        if(username === null){
            setMsg("Username cannot be blank")
            return 
        }
        if(password == null){
            setMsg("Password cannot be blank")
            return 
        }

        console.log('i am on line 17')
        alert('alright, u r ready to be tested!!!!')
    }
    
    return(
        <div>
            <div className="container-fluid">
                
                <div className="row mb-4">
                    <div className="col-lg-12">
                        <nav class="navbar navbar-light bg-light">
                            <div class="container-fluid">
                                <span class="navbar-brand mb-0 h1">Navbar</span>
                            </div>
                        </nav>
                    </div>    
                </div>
                <br /><br /><br />
                <div className="row mt-4">
                    <div className="col-sm-4">
                        
                    </div>
                    <div className="col-sm-4">
                        <div className="card">
                            <div className="card-header">
                                Login
                            </div>
                            <div class="card-body">
                                <div className="mb-4">
                                    {msg}
                                </div>
                                <div className="mb-4">
                                    <label>Username: </label>
                                    <input type="text" className="form-control" 
                                        onChange={$event=>setUsername($event.target.value) } />
                                </div>
                                <div className="mb-4">
                                    <label>Password: </label>
                                    <input type="password" className="form-control" 
                                        onChange={($event)=>{setPassword($event.target.value)}}/>
                                </div>
                                <div className="mb-4">
                                <button type="button" class="btn btn-primary" onClick={()=>{login()}}>Login</button>
                                </div>
                            </div>
                            <div className="card-footer">
                                Don't have an Account? Sign Up <br />
                                Reset Password
                            </div>
                        </div>
                    </div>
                    <div className="col-sm-4">
                        
                    </div>

                </div>
            </div>
        </div>
    )
}

export default Login;