function Login(){
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
                        <div class="card">
                            <div className="card-header">
                                Login
                            </div>
                            <div class="card-body">
                                <div className="mb-4">
                                    <label>Username: </label>
                                    <input type="text" className="form-control" />
                                </div>
                                <div className="mb-4">
                                    <label>Password: </label>
                                    <input type="password" className="form-control"/>
                                </div>
                                <div className="mb-4">
                                <button type="button" class="btn btn-primary">Login</button>
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