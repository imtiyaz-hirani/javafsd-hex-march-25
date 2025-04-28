import axios from "axios";
import { setAlbums } from "../albumSlice";
const fetchAlbums = () => async (dispatch) => {
    //fetch the data by calling API 
    const resp = await axios.get('https://jsonplaceholder.typicode.com/albums');

    //dispatch the data to reducer fetchAlbums() 
    dispatch(setAlbums({ albums: resp.data }))
}

export default fetchAlbums;