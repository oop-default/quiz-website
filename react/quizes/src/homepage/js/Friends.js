import React,{Component} from 'react';
import '../css/Friends.css';

const friends = [
    {id: 1, nick: "vigaca", isActive: true},
    {id: 2, nick: "vigaca", isActive: true},
    {id: 3, nick: "vigaca", isActive: true},
    {id: 4, nick: "vigaca", isActive: false},
    {id: 5, nick: "vigaca", isActive: false},
    {id: 6, nick: "vigaca", isActive: true},
    {id: 7, nick: "vigaca", isActive: true},
    {id: 8, nick: "vigaca", isActive: false},
    {id: 9, nick: "vigaca", isActive: true},
    {id: 0, nick: "vigaca", isActive: false},
    {id: 11, nick: "vigaca", isActive: false},
    {id: 12, nick: "vigaca", isActive: true},
    {id: 13, nick: "vigaca", isActive: true},
    {id: 14, nick: "vigaca", isActive: false},
    {id: 15, nick: "vigaca", isActive: true},
    {id: 16, nick: "vigaca", isActive: false},
    {id: 17, nick: "vigaca", isActive: false},
    {id: 18, nick: "vigaca", isActive: true},
    {id: 19, nick: "vigaca", isActive: true},
    {id: 10, nick: "vigaca", isActive: false},
    {id: 20, nick: "vigaca", isActive: true}
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
                <table className="friendsTable">
                    <tbody>
                        {
                        this.state.friends.map((friend) => {
                            return <tr key={friend.id}>
                                <td><a style={{textDecoration:"none"}} href={'http://localhost:3000/profile?id=' + friend.id}>{friend.nick}</a></td>
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
