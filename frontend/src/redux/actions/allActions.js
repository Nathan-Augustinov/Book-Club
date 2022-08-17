// export const increment = (nr) => {
//     return {
//         type: 'INCREMENT',
//         payload: nr
//     };
// }

// export const decrement = () => {
//     return {
//         type: 'DECREMENT'
//     };
// }

export const login = (userDetails) => {
    return {
        type: 'LOG_IN',
        payload: userDetails
    }
}

export const logout = () => {
    return {
        type: 'LOG_OUT'
    }
}