import React,{Component} from 'react';
import './FriendsBar.css';

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

function FriendsBar(page){
    let friendsBarDrawer;
        if(page.state.friendsBarOpen){
            return ( <div className = "openedFriendsBar">
                <table className="friendsTable">
                        {
                        friends.map((friend) => {
                            return (<tr>
                            <td><a style={{color:"white"}} href="https://www.facebook.com">{friend.nick}</a></td>
                            <td>{friend.isActive ? (<span className="greenDot"></span>) :  <span className="redDot"></span>}</td>
                            </tr>)
                        })
                        }
                </table>
            </div>
            );
       
    }else{
        return(
            null
        );
    }
}
export default FriendsBar