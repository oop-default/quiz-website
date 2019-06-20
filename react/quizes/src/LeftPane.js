import React,{Component} from 'react';
import FriendsList from './FriendsList'
import UserInfo from './UserInfo'
import '../css/leftPane.css'


class LeftPane extends Component {
    constructor() {
      super();
    }

    render() {
        return (
            <div className ="leftpane">
                <UserInfo></UserInfo>
                <FriendsList></FriendsList>
            </div>
        );
    }
}

export default LeftPane;
