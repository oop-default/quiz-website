import React,{Component} from 'react';
import '../css/notifications.css';
import AcceptOrDenie from './acceptOrDenie';

const requests = [
    {from: "vigaca"},
    {from: "vigaca"},
    {from: "vigaca"},
    {from: "vigaca"},
    {from: "vigaca"},
    {from: "vigaca"},
    {from: "vigaca"}
];

const messages = [
    {from: "vigaca", note: "ragaca"},
    {from: "vigaca", note: "ragaca"},
    {from: "vigaca", note: "ragaca"},
    {from: "vigaca", note: "ragaca"},
    {from: "vigaca", note: "ragaca"},
    {from: "vigaca", note: "ragaca"},
    {from: "vigaca", note: "ragaca"},
    {from: "vigaca", note: "ragaca"},
    {from: "vigaca", note: "ragaca"},
    {from: "vigaca", note: "ragaca"},
    {from: "vigaca", note: "ragaca"},
    {from: "vigaca", note: "ragacniefnasbdaiubsduiasndniuasdniuasnduiadsa"}
];

const Challenges = [
    {from: "vigaca", quiz_id: 1}
];

class notifications extends Component{

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
                    {
                        this.state.messages.map((notification) => {
                            return <tr>
                                <td id = "notHide" colSpan="3" style={{wordBreak:"break-word"}}>
                                    <a href="https://www.facebook.com"><h6 className="from">{notification.from}:</h6></a>
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
                            return <tr>
                                <td id="notHide" colSpan="3" style={{textAlign:"center"}}>
                                    <a href="https://www.facebook.com">
                                        {notification.from} challenged you to take quiz number {notification.quiz_id}
                                    </a>
                                    <AcceptOrDenie></AcceptOrDenie>
                                </td>
                            </tr>
                        })
                    }
                </tbody>
                <tbody className="notsBody">
                    {
                        this.state.requests.map((notification) => {
                            return <tr>
                                <td id="notHide" colSpan="3" style={{textAlign:"center"}}>
                                    <a href="https://www.facebook.com">{notification.from} sent you a friend request</a>
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
                document.getElementsByClassName("heads")[index].style = "background-color: rgb(7, 121, 7)";
            }
        }
        if(getComputedStyle(ele, null).display === "none") {
            document.getElementsByClassName("heads")[nOfElement].style = "background-color: #1dc407";
            ele.style.display = "block";
        } else {
            document.getElementsByClassName("heads")[nOfElement].style = "background-color: rgb(7, 121, 7)";
            ele.style.display = "none";
        }
    }

}



export default notifications;
