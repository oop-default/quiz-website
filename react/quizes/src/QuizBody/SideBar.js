import React from 'react';
import './SideBar.css';

function topUsersForm(topScores,scoreType){
    return (
      <div>
        <div className = "rankingHeader">{scoreType}</div>
    <table>
      <tr>
        <th className = "rankingPos">#</th>
        <th className = "rankingTh">User</th>
        <th className = "rankingTh">Score</th>
      </tr>
      {
      topScores.map((info) => {
        return <tr>
          <td className = "rankingPos">{info.pos}</td>
          <td className = "rankingTd">{info.user}</td>
          <td className = "rankingTd">{info.score}</td>
        </tr>
      })
    }
    </table>
    </div>
    );
  }
  function PersonalTop(topScores){
    return (
    <div>
      <div className = "rankingHeader">Personal' top scores</div>
    <table>
      <tr>
        <th className = "rankingPos">#</th>
        <th className = "rankingTh">Score</th>
      </tr>
      {
      topScores.map((info) => {
        return <tr>
          <td className = "rankingPos">{info.pos}</td>
          <td className = "rankingTd">{info.score}</td>
        </tr>
      })
    }
    </table>
    </div>
    );
  }
function SideBar(topScores,friendTopScores,previousTries,page){
  let sideBarContent;
  let sideBarDrawer;
  var style;
  var globalTop = "Global top scores";
  var friendsTop = "Friends' top scores";
  
  if(page.state.sideBarOpen){
    sideBarContent = [
      topUsersForm(topScores,globalTop),
      topUsersForm(friendTopScores,friendsTop),
      PersonalTop(previousTries)
    ]
    if(page.state.quizStarted|| page.state.quizSummary){
      sideBarDrawer = (
      <div><a href="#" className="sideDrawerButtonArea" onClick = {()=>handle(page)}>
            <span className = "sideDrawerButton">&#10006;</span>
            </a>
      </div>
    );
    }
    style = "openedSideBar";
  }else{
    sideBarContent = (
      null
    );
    if(page.state.quizStarted || page.state.quizSummary){
    sideBarDrawer = (
      <div><a href="#" className="sideDrawerButtonArea" onClick = {()=>handle(page)}>
            <span className = "sideDrawerButton">&#9776;</span>
            </a>
      </div>
    );
    }
    style = "closedSideBar";
  }
  return(<div className = {style}>
          {sideBarDrawer}
          {sideBarContent}
        </div>);
}
function handle(page){
  var bool = page.state.sideBarOpen;
  page.setState({
    sideBarOpen : !bool,
    friendsBarOpen : !bool,
  });
}
export default SideBar