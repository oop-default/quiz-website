import React,{Component} from 'react';
import './Friends.css';

const friends = [
    {nick: "vigaca", isActive: true},
    {nick: "vigaca", isActive: true},
    {nick: "vigaca", isActive: true},
    {nick: "vigaca", isActive: false},
    {nick: "vigaca", isActive: false},
    {nick: "vigaca", isActive: true},
    {nick: "vigaca", isActive: true},
    {nick: "vigaca", isActive: false},
    {nick: "vigaca", isActive: true},
    {nick: "vigaca", isActive: false},
    {nick: "vigaca", isActive: false},
    {nick: "vigaca", isActive: true},
    {nick: "vigaca", isActive: true},
    {nick: "vigaca", isActive: false},
    {nick: "vigaca", isActive: true},
    {nick: "vigaca", isActive: false},
    {nick: "vigaca", isActive: false},
    {nick: "vigaca", isActive: true},
    {nick: "vigaca", isActive: true},
    {nick: "vigaca", isActive: false},
    {nick: "vigaca", isActive: true}
  ];

class Friends extends Component{
    constructor(){
        super();
        this.state = {
            friends: friends
        };
    }

    render() {
        return (
            <div className = "friends">
                <div style = {{textAlign : "center",position : "sticky"}} className = "scoreBar">Your Friends</div>
                <table className="friendsTable">
                    <tbody>
                        {
                        this.state.friends.map((friend) => {
                            return <tr>
                            <td><a style={{textDecoration:"none"}} href="https://www.facebook.com">{friend.nick}</a></td>
                            <td>{friend.isActive ? (<span className="greenDot"></span>) :  <span className="redDot"></span>}</td>
                            
                            </tr>
                        })
                        }
                    </tbody>
                </table>
            </div>
        );
    }


}

export default Friends;
