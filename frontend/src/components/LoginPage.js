import React from "react"
import axios from "axios";
import { useState } from "react";
import logoImage from "../resources/logo.png"
import bookshelfImage_part1 from "../resources/bookshelf_part1.png"
import bookshelfImage_part2 from "../resources/bookshelf_part2.png"
import { useNavigate } from "react-router-dom";

const LoginPage = () => {
    let navigate = useNavigate();
    const routeChange = () => {
        let path = '../dashboard';
        navigate(path);
    }

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    async function handleSubmit(){
        try{ 
            await axios.post("/api/users/login",
            {
                username: username,
                password: password
            });

            alert("User logged in successfully");

            setUsername("");
            setPassword("");
           
            routeChange();
        }
        catch{
            alert("User log in failed");
        }
    }

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