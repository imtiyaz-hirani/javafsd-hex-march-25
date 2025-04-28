import axios from "axios";
import { setUsers } from "../userSlice";

const fetchUsers = () => async (dispatch) => {
    //call the api to fetch data and take the resp 
    const resp = await axios.get('https://jsonplaceholder.typicode.com/users');
    //now let me dispatch this data to reducer function 
    dispatch(setUsers({ users: resp.data }))
}


export default fetchUsers
