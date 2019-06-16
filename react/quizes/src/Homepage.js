import React,{Component} from 'react';
import NavBar from './NavBar'
import Middle from './Middle'



class Homepage extends Component {

  constructor() {
    super();
  }



  render() {
    return(
      <div onClick={(e)=>this.hideAllNots(e)}>
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
      document.getElementsByClassName("heads")[index].style = "background-color: rgb(7, 121, 7)";
  }
}

}

export default Homepage;
