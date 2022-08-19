import { createSlice } from "@reduxjs/toolkit"
import update from 'immutability-helper';
const allBooksReducer = createSlice({
    name: 'allBooks',
    initialState: [],
    reducers:{
        addAllBooks: (state, action) => {
            console.log(action.payload);
            return update(state, {$set: action.payload});
        }
    }
})

export const {addAllBooks} = allBooksReducer.actions;
export default allBooksReducer.reducer;