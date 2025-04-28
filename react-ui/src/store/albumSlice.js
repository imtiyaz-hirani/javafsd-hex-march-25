import { createSlice } from "@reduxjs/toolkit";

const albumSlice = createSlice({
    name: "albums",
    initialState: {
        albums: []
    },
    reducers: {
        setAlbums(state, action) { //data comes from action. state is the local store 
            state.albums = action.payload.albums   //payload: {albums : resp.data} coming from action
        }
    }
})
export const { setAlbums } = albumSlice.actions;

export default albumSlice.reducer

/** 
 *  createSlice func has 3 params 
 *  1. name : ""
 *  2. initialState : {}
 *  3. reducers: {}
 * 
 */