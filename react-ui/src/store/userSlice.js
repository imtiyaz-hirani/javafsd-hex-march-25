import { createSlice } from "@reduxjs/toolkit";

const userSlice = createSlice({
    name: "users",
    initialState: {
        users: []
    }
    , reducers: {
        setUsers(state, action) {
            state.users = action.payload.users
        }
    }
});

export const { setUsers } = userSlice.actions;

export default userSlice.reducer; 