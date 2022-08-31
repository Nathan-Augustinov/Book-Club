import { createSlice } from "@reduxjs/toolkit"
import update from 'immutability-helper';
const waitingListReducer = createSlice({
    name: 'waitingList',
    initialState: [],
    reducers:{
        addWaitingListBooks: (state, action) => {
            return update(state, {$set: action.payload});
        }
    }
})

export const {addWaitingListBooks} = waitingListReducer.actions;
export default waitingListReducer.reducer;