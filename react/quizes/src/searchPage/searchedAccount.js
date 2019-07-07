import React from 'react';
import './searchedAccount.css'

export function searchedAccount(data,page) {
    var username = data.username;
    var name = data.firstname + " " + data.secondname;
    var parent = page;
    return (
        <div>
            <div id="srchAccB1">
                <div>
                    <image id="srchAccImage">image </image>
                    <div id="srchAccBody">
                        <label id="srchAccUsernmaeLbl" >{username}</label>
                    </div>
                </div>
                <div>
                    <label id="srchAccNameLbl">{name}</label>
                </div>
                <button id="srchAccBtn" onClick={e => clicked(e, parent)} value={username}>View Profile</button>
            </div>
            
        </div>
		);        
}

function clicked(e,page) {
    page.props.history.push("/profile/" + e.target.value);
}