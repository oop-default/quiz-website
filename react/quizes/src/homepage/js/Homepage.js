import React,{Component} from 'react';
import NavBar from './NavBar'
import Middle from './Middle'
import { withRouter } from 'react-router-dom'
import { getJWT } from '../../Jwt';
 
class Homepage extends Component {

  constructor(props) {
      super(props);
      console.log("homepage");
  }

  componentDidMount() {
    //  const jwt = getJWT();
    //  if (!jwt) {
    //       this.props.history.push("/login");
    //  }
      //make fetch get to API about this user to not succes request this.props.history.push(/login); localstorage.remove("cool-jwt")
  }

  render() {
      return (
          <div onClick={(e) => this.hideAllNots(e)}>
            <Middle></Middle>
            <NavBar></NavBar>
          </div>
    );
  }

hideAllNots(e) {
  if(e.target.id === "notHide") {
    return;
  }
  var ele = document.getElementsByClassName("notsBody");
  for (let index = 0; index < ele.length; index++) {
      ele[index].style.display = "none";
      document.getElementsByClassName("heads")[index].style = "background-color: forestgreen";
  }
}

}

export default withRouter(Homepage);
