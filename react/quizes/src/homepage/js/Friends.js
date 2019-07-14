import React,{Component} from 'react';
import '../css/Friends.css';

const friends = [
    {id: 1, nick: "vigaca"},
    {id: 2, nick: "vigaca"},
    {id: 3, nick: "vigaca"},
    {id: 4, nick: "vigaca"},
    {id: 5, nick: "vigaca"},
    {id: 6, nick: "vigaca"},
    {id: 7, nick: "vigaca"},
    {id: 8, nick: "vigaca"},
    {id: 9, nick: "vigaca"},
    {id: 0, nick: "vigaca"},
    {id: 11, nick: "vigaca"},
    {id: 12, nick: "vigaca"},
    {id: 13, nick: "vigaca"},
    {id: 14, nick: "vigaca"},
    {id: 15, nick: "vigaca"},
    {id: 16, nick: "vigaca"},
    {id: 17, nick: "vigaca"},
    {id: 18, nick: "vigaca"},
    {id: 19, nick: "vigaca"},
    {id: 10, nick: "vigaca"},
    {id: 20, nick: "vigaca"}
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
            <div className = "homeFriends">
                <table className="friendsTable">
                    <tbody>
                        {
                        this.state.friends.map((friend, i) => {
                            return <tr key={i}>
                                <td><a style={{textDecoration:"none"}} href={'http://localhost:3000/profile/' + friend.id}>{friend.nick}</a></td>
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
