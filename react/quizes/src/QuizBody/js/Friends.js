import React,{Component} from 'react';
import '../css/Friends.css';

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
            <div className = "friendsHeader">
                <div style = {{textAlign : "center"}} className = "scoreBar">Your Friends</div>
            <div className = "friends">
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
            </div>
        );
    }


}

export default Friends;
