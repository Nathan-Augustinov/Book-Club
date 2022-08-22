import { createSlice } from "@reduxjs/toolkit"
import update from 'immutability-helper';
const rentedBooksReducer = createSlice({
    name: 'rentedBooks',
    initialState: [],
    reducers:{
        addAllRentedBooks: (state, action) => {
            return update(state, {$set: action.payload});
        }
    }
})

export const {addAllRentedBooks} = rentedBooksReducer.actions;
export default rentedBooksReducer.reducer;