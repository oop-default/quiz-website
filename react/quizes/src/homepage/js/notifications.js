import React,{Component} from 'react';
import '../css/notifications.css';
import AcceptOrDenie from './acceptOrDenie';

const requests = [
    {fromId: 1, from: "vigaca"},
    {fromId: 2, from: "vigaca"},
    {fromId: 3, from: "vigaca"},
    {fromId: 4, from: "vigaca"},
    {fromId: 5, from: "vigaca"},
    {fromId: 6, from: "vigaca"},
    {fromId: 7, from: "vigaca"}
];

const messages = [
    {fromId: 1, from: "vigaca", note: "ragaca"},
    {fromId: 2, from: "vigaca", note: "ragaca"},
    {fromId: 3, from: "vigaca", note: "ragaca"},
    {fromId: 4, from: "vigaca", note: "ragaca"},
    {fromId: 5, from: "vigaca", note: "ragaca"},
    {fromId: 6, from: "vigaca", note: "ragaca"},
    {fromId: 7, from: "vigaca", note: "ragaca"},
    {fromId: 8, from: "vigaca", note: "ragaca"},
    {fromId: 9, from: "vigaca", note: "ragaca"},
    {fromId: 0, from: "vigaca", note: "ragaca"},
    {fromId: 11, from: "vigaca", note: "ragaca"},
    {fromId: 13, from: "vigaca", note: "ragacniefnasbdaiubsduiasndniuasdniuasnduiadsa"}
];

const Challenges = [
    {fromId: 1, from: "vigaca", quiz_id: 1}
];

class Notifications extends Component{

    constructor() {
        super();
        this.state={
            requests:requests,
            messages:messages,
            Challenges:Challenges
        }
    }

    render() { 
        return (
        <div className="nots">
            <table className = "notsTable">
                <thead style={{width:"330px"}}>
                    <tr>
                    <th className="heads"><button onClick={()=>this.show("message")} className="notification" id="notHide">
                            <span id="notHide">Messages</span>
                            <span className="badge" id="notHide">3</span>
                        </button>
                    </th>
                    <th className="heads"><button onClick={()=>this.show("challenge")} className="notification" id="notHide">
                            <span id="notHide">Challenges</span>
                            <span className="badge" id="notHide">3</span>
                        </button>
                    </th>
                    <th className="heads"><button onClick={()=>this.show("request")} className="notification" id="notHide">
                            <span id="notHide">Requests</span>
                            <span className="badge" id="notHide">3</span>
                        </button>
                    </th>
                    </tr>
                </thead>
                <tbody className="notsBody">
                    <tr>
                        <td id = "notHide">
                            <input id = "notHide" style={{width:"100px"}} placeholder="Enter receiver"></input>
                            <input id = "notHide" style={{width:"250px"}} placeholder="Enter message"></input>
                            <button id = "notHide" onClick={()=>this.reply()} style={{float:"right"}}>send</button>
                        </td>
                    </tr>
                    {
                        this.state.messages.map((notification) => {
                            return <tr key={notification.fromId}>
                                <td id = "notHide" colSpan="3" style={{wordBreak:"break-word"}}>
                                    <a href={"http://localhost:3000/profile/:userId"}><h6 className="from">{notification.from}:</h6></a>
                                    <div id = "notHide" style={{overflow:"auto"}}>{notification.note}</div>
                                    <input className="sendingMessage" id = "notHide" style={{width:"250px"}}></input>
                                    <button id = "notHide" onClick={()=>this.reply()} style={{float:"right"}}>reply</button>
                                </td>
                            </tr>
                        })
                    }
                </tbody>
                <tbody className="notsBody">
                    {
                        this.state.Challenges.map((notification) => {
                            return <tr key={notification.fromId}>
                                <td id="notHide" colSpan="3" style={{textAlign:"center"}}>
                                    <a href={"http://localhost:3000/profile/:userId"}>
                                        {notification.from}{notification.quiz_id}
                                    </a> challenged you to take quiz number 
                                    <AcceptOrDenie></AcceptOrDenie>
                                </td>
                            </tr>
                        })
                    }
                </tbody>
                <tbody className="notsBody">
                    {
                        this.state.requests.map((notification) => {
                            return <tr key={notification.fromId}>
                                <td id="notHide" colSpan="3" style={{textAlign:"center"}}>
                                    <a href={"http://localhost:3000/profile/:userId"}>{notification.from}</a> sent you a friend request
                                    <AcceptOrDenie></AcceptOrDenie>
                                </td>
                            </tr>
                        })
                    }
                </tbody>
            </table>
        </div>);
    }

    reply() {
        var messageToSend = document.getElementsByClassName("sendingMessage")[0].value;
        document.getElementsByClassName("sendingMessage")[0].value = "";
    }


    show(notification) {
        var nOfElement;
        var ele;
        if(notification ==="message") {
            nOfElement = 0
            ele = document.getElementsByClassName("notsBody")[0];
        } else if(notification === "challenge"){
            nOfElement = 1;
            ele = document.getElementsByClassName("notsBody")[1];
        } else {
            nOfElement = 2;
            ele = document.getElementsByClassName("notsBody")[2];
        }

        for (let index = 0; index < 3; index++) {
            if(index !== nOfElement) {
                document.getElementsByClassName("notsBody")[index].style.display = "none";
                document.getElementsByClassName("heads")[index].style = "background-color: forestgreen";
            }
        }
        if(getComputedStyle(ele, null).display === "none") {
            document.getElementsByClassName("heads")[nOfElement].style = "background-color: #1dc407; border-radius: 5px";
            ele.style.display = "block";
        } else {
            document.getElementsByClassName("heads")[nOfElement].style = "background-color: forestgreen";
            ele.style.display = "none";
        }
    }

}



export default Notifications;
