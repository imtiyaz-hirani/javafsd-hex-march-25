 import { useState } from 'react';
import './App.css'

function App() { //app is a component and a component must have a return 
 
  let [msg,setMsg] = useState(''); 
  let [name] = useState('john'); 
   
  const age=65;

  //age = 46; 
  name='emma';

  let x = 10;
  let y = 5; 


  const clickMeFn = ()=>{
    //alert('I am IN!!!')
     setMsg("U clicked a button");
     //console.log(this.msg)
  }

  
  return(
    <div>
      <h1>My React App</h1>
      <p>Name is {name} </p>
      <p>Age is {age}</p>
      <p>Mul is {x*y}</p>

      <hr></hr>
      <p>
       {
          age>60?"You are a Senior Citizen"
          :age>20?"You are a Middle Age Citizen":"You are a Teenager"
       }
      </p>
      <hr />
      <button onClick={()=>{ clickMeFn() }}>Click ME!!!</button>
      <p>{msg}</p>
    </div>
  )
}

export default App
