import { useState } from "react";
import users from '../../data/users.js'

function Login(){
    const [username,setUsername] = useState(null);
    const [password,setPassword] = useState(null);
    const [msgUsername,setMsgUsername] = useState(null);
    const [msgPassword,setMsgPassword] = useState(null);
    const [userData,setUserData] = useState(users); 

    const login = ()=>{
        let isCorrect = false; 

        if(username === null ||  username === "" || username === undefined){
            setMsgUsername("Username cannot be blank")
            return
        }
        else{
            setMsgUsername(null)
        }

        if(password == null || password === "" || password === undefined){
            setMsgPassword("Password cannot be blank")
            return
        }
        else{
            setMsgPassword(null)
        }
        
        //check username password by calling API else use file data 
        userData.forEach(u => {
            if(u.username === username  && u.password === password){
                alert("alright u r legit, u role is  " + u.role)
                isCorrect = true 
            }
            if(isCorrect === false){
                setMsgUsername("Invalid Credentials")
            }
        });
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
                                {
                                    msgUsername === null ? "" : <div className="mb-4">
                                        {msgUsername}
                                    </div>
                                }
                                 {
                                    msgPassword === null ? "" : <div className="mb-4">
                                        {msgPassword}
                                    </div>
                                }
                                <div className="mb-4">
                                    <label>Username: </label>
                                    <input type="text" className="form-control" 
                                        onChange={$event=>{
                                                setUsername($event.target.value); 
                                                setMsgUsername(null) 
                                                } } />
                                </div>
                                <div className="mb-4">
                                    <label>Password: </label>
                                    <input type="password" className="form-control" 
                                        onChange={($event)=>{
                                            setPassword($event.target.value); 
                                            setMsgPassword(null)
                                        }}/>
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