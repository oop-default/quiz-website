export function changeTable(that, nOfTable) {
    if(that.state.nOfTable === 0) {
        that.setState({
          nOfTable:1
        });
      } else {
        that.setState({
          nOfTable:0
        });
      }
}