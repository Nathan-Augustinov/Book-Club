import React, { useEffect } from "react"
import { useState } from "react";
import logoImage from "../../resources/logo.png"
import bookshelfImage_part1 from "../../resources/bookshelf_part1.png"
import bookshelfImage_part2 from "../../resources/bookshelf_part2.png"
import { useNavigate } from "react-router-dom";
import "./LoginPage.css";
import { useDispatch } from 'react-redux';
import { loginUser } from "../../redux/reducers/userReducer";

const LoginPage = () => {
    const dispatch = useDispatch();
    let navigate = useNavigate();
    const routeChange = () => {
        let path = '../dashboard';
        navigate(path);
    }

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const resetForm = () => {
        setUsername("");
        setPassword("");
    };

    const Login = async (values) => {
        const response = await fetch("http://localhost:8080/api/users/login",{
            method: "POST",
            headers:{
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(values)
        });

        if(response && !response.ok){
            const error = await response.text();
            alert(error);
            throw new Error(error);
        }

        return response.json();
    }

    async function handleSubmit(){

        const values = {
            username: username,
            password: password
        };

        Login(values)
            .then((data)=>{
                localStorage.setItem("token", data.token);
                resetForm();
                routeChange();
                dispatch(loginUser(data));
            })
            .catch(err => {
                console.log(err);
            })
    }

    useEffect(()=>{
        let token = localStorage.getItem('token');
        if(token && token !== undefined){
            navigate("/dashboard");
        }
    }, [])

    return (
        <div>
            <div className="startPageHeader">
                <img className="logoImage" src={logoImage} alt=""/>
            </div>
            <div className="div_container">
                <div className="image_div flex_child">
                    <img className="bookshelfImage" src={bookshelfImage_part1} alt=''/>
                </div>
                <div className="flex_child">
                    <div className="login_form">
                        <p className="LoginMessage">Log into your TMD Book Club account</p>
                        <div className="form_body">
                            <div className="username">
                                <label className="form_label">Username </label>
                                <input  type="text" id="username" className="form_input" onChange={(event) => {setUsername(event.target.value)}}/>
                            </div>
                            <div className="password">
                                <label className="form_label">Password </label>
                                <input className="form_input" type="password"  id="password" onChange={(event) => {setPassword(event.target.value)}}/>
                            </div>
                        </div>
                        <div className="footer">
                            <button type="submit" className="btn" onClick={handleSubmit}>Login</button>
                        </div>
                    </div> 
                </div>
                <div className="image_div flex_child">
                    <img className="bookshelfImage" src={bookshelfImage_part2} alt=''/>
                </div>
            </div>
        </div>
    );
}

export default LoginPage;