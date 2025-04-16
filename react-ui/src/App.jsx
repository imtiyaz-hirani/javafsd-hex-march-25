 import { useState } from 'react';
import './App.css'

function App() { //app is a component and a component must have a return 
 
  const [name,setName] = useState(null);
  const [email,setEmail] = useState(null);
  const [contact,setContact] = useState(null); 
  
  const populate = ()=>{
    setName("harry potter") 
    setEmail("harry@gmail.com")
    setContact("9566985669")
  }
  return(
    <div>
      <button onClick={()=>{ populate() }}>Populate Values</button>
        <p>
          Name: {name} <br />
          Email: {email} <br />
          Contact: {contact} <br />
        </p>
    </div>
  )
}

export default App
