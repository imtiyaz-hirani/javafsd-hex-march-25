 import { useEffect, useState } from 'react';
import './App.css'

function App() { //app is a component and a component must have a return 
 
   const [employeeObj,setEmployeeObj] = useState({}); 
   const [managerObj,setManagerObj] = useState({}); 
   const [showEmp,setShowEmp] = useState(false); 
   const [showManager,setShowManager] = useState(false); 

   useEffect(()=>{
    const populate = ()=>{
      /** Employee object created with its fields  */
      let empObj = {
        "name":"harry Potter",
        "email" : "emp@gmail.com",
        "contact" : "66599856965",
        "projectInfo" : "this is the proj emp is working on"
      }
  
      /** Manager object created with its fields  */
      let managerObj = {
        "name":"albus dumbledore",
        "email" : "manager@gmail.com",
        "contact" : "566698555698",
        "departmentInfo" : "IT Department",
        "employeeInfo": "Manages 20 employees"
      }
  
      /** Assign this local objects to states, so that we can display 
       * states in other functions */
  
      setEmployeeObj(empObj)
      setManagerObj(managerObj)
    }
      
    populate();  
    },[]);

    
    const populate = (val)=>{
      switch(val){
        case 'employee':
          setShowEmp(true)  
          setShowManager(false)
          break; 
        case 'manager':
          setShowEmp(false)  
          setShowManager(true)
          break; 
        default:
          setShowEmp(false)  
          setShowManager(false)
          break; 
      }
    }

    return(
    <div>
      <button onClick={()=>{ populate('employee') }}>Populate Employee</button> 
      <button onClick={()=>{ populate('manager') }}>Populate Manager</button>

        {
          showEmp === false? "" : 
          <div id='employeeContent'>
          <h3>Employee Block</h3>
            <div>
              Name: {employeeObj.name} <br />
              Email: {employeeObj.email} <br />
              Contact: {employeeObj.contact} <br />
              <br />
              <h4>Project Info</h4>
              {employeeObj.projectInfo}
            </div>
         </div>
        }
       
        {
          showManager === false? "": 
          <div id='managerContent'>
          <h3>Manager Block</h3>
           <div>
             Name: {managerObj.name} <br />
             Email: {managerObj.email} <br />
             Contact: {managerObj.contact} <br />
             <br />
             <h4>Department Info</h4>
             <span>{managerObj.departmentInfo} </span> 
             <h4>All Employee Info</h4>
             <span> {managerObj.employeeInfo}</span>
           </div>
          </div>
        }
        
    </div>
  )
}

export default App
