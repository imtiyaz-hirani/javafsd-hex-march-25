import { configureStore } from "@reduxjs/toolkit";
import albumSlice from './albumSlice'

const store = configureStore({
    reducer: {
        albums: albumSlice   //regisering the reducer 
    }
})

export default store; 