import React from "react";
import PeriodRow from "./PeriodRow";
import axios from "axios";

class PeriodTable extends React.Component {
  state = {
    periods: [],
    period_id: "",
    start_time: "",
    end_time: "",
    day: "",
  };

  handleSubmit = (event) => {
    event.preventDefault();

    axios
      .post("https://tutor-booking-system.herokuapp.com/api/filter-for-periods", {
        period_id: this.state.period_id,
        start_time: this.state.start_time,
        end_time: this.state.end_time,
        day: this.state.day,
      })
      .then((res) => {
        const periods = res.data;
        this.setState({ periods });
      });
  };

  componentDidMount() {
    axios
      .get("https://tutor-booking-system.herokuapp.com/api/periods")
      .then((res) => {
        const periods = res.data;
        this.setState({ periods });
      })
      .catch((error) => console.log(error));
  }

  handleChange_period_id = (event) => {
    this.setState({ period_id: event.target.value });
  };

  handleChange_start_time = (event) => {
    this.setState({ start_time: event.target.value });
  };

  handleChange_end_time = (event) => {
    this.setState({ end_time: event.target.value });
  };

  handleChange_day = (event) => {
    this.setState({ day: event.target.value });
  };

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <div className="row">
            <div className="col-3">
              <input
                className="form-control"
                id="period-id"
                name="period_id"
                onChange={this.handleChange_period_id}
                placeholder="Enter Period ID"
                type="text"
              />
            </div>
            <div className="col-3">
              <input
                className="form-control"
                id="start-time"
                name="start_time"
                onChange={this.handleChange_start_time}
                placeholder="Enter start time"
                type="text"
              />
            </div>
            <div className="col-3">
              <input
                className="form-control"
                id="end-time"
                name="end_time"
                onChange={this.handleChange_end_time}
                placeholder="Enter end time"
                type="text"
              />
              <datalist id="province">
                <option value="123" />
              </datalist>
            </div>
            <div className="col-2">
              <input
                className="form-control"
                id="day"
                list="day"
                name="day"
                onChange={this.handleChange_day}
                placeholder="Enter Day"
              />
            </div>
            <div className="col-1 d-flex justify-content-end">
              <button
                className="btn btn-primary"
                style={{ width: "100%" }}
                type="submit"
              >
                Search
              </button>
            </div>
          </div>
        </form>
        <div className="table-responsive border border-dark mt-3">
          <table className="table table-striped">
            <thead className="sticky-top">
              <tr className="bg-success">
                <th className="text-light">Period ID</th>
                <th className="text-light">Tutor ID</th>
                <th className="text-light">Student ID</th>
                <th className="text-light">Start time</th>
                <th className="text-light">End time</th>
                <th className="text-light">Day</th>
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
        </div>
      </div>
    );
  }
}

export default PeriodTable;
