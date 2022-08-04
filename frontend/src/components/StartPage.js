import React from "react"
import logoImage from "../resources/logo.png"
import bookshelfImage from "../resources/bookshelf.jpg"
import { useNavigate } from "react-router-dom";
const StartPage = () => {
    let navigate = useNavigate();
    const routeChange = () => {
        let path = 'login';
        navigate(path);
    }
    return (
        <div>
            <div className="startPageHeader">
                <img className="logoImage" src={logoImage} alt=""/>
            </div>
            <div>
                <div className="div_container">
                    <div className="flex_child">
                        <div className="register_form">
                            <p className="signUpMessage">Sign up to TMD Book Club</p>
                            <div className="form_body">
                                <div className="firstname">
                                    <label className="form_label" for="firstName">First Name </label>
                                    <input className="form_input" type="text" id="firstName" />
                                </div>
                                <div className="lastname">
                                    <label className="form_label" for="lastName">Last Name </label>
                                    <input  type="text" name="" id="lastName"  className="form_input" />
                                </div>
                                <div className="email">
                                    <label className="form_label" for="email">Email </label>
                                    <input  type="email" id="email" className="form_input"/>
                                </div>
                                <div className="username">
                                    <label className="form_label" for="username">Username </label>
                                    <input  type="text" id="username" className="form_input"/>
                                </div>
                                <div className="password">
                                    <label className="form_label" for="password">Password </label>
                                    <input className="form_input" type="password"  id="password"/>
                                </div>
                                <div className="confirm-password">
                                    <label className="form_label" for="confirmPassword">Confirm Password </label>
                                    <input className="form_input" type="password" id="confirmPassword"/>
                                </div>
                            </div>
                            <div class="footer">
                                <button type="submit" class="btn" onClick={routeChange}>Sign Up</button>
                            </div>
                            <div>
                                <p>Already have an account?</p>
                                <button type="submit" class="btn" onClick={routeChange}>Login</button>
                            </div>
                        </div> 
                    </div>   
                    <div className="flex_child">
                        <img className="bookshelfImage" src={bookshelfImage} alt=''/>
                    </div>
                </div>
            </div>
        </div>
        
    )
}

export default StartPage;
