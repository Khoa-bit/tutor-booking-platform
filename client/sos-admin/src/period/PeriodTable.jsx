import React from "react";
import PeriodRow from "./PeriodRow";
import axios from "axios";

class PeriodTable extends React.Component {
  state = {
    periods: []
  };

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/periods")
      .then((res) => {
        const periods = res.data;
        this.setState({ periods });
      })
      .catch((error) => console.log(error));
  }

  render() {
    return (
      <table className="table table-striped">
        <thead className="sticky-top">
          <tr className="bg-success">
            <th className="text-light">Period ID</th>
            <th className="text-light">Start time</th>
            <th className="text-light">End time</th>
            <th className="text-light">Day</th>
            <th className="text-light"></th>
            <th className="text-light"></th>
          </tr>
        </thead>
        <tbody>
          {/*{this.state.periods.map((period) => (
            <periodRow period={period} />
          ))} */}

          {Object.keys(this.state.periods).map((key) => (
            <PeriodRow key={key} period={this.state.periods[key]} />
          ))}
        </tbody>
      </table>
    );
  }
}

export default PeriodTable;
