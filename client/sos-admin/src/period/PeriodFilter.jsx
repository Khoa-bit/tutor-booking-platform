import React from "react";

class PeriodFilter extends React.Component {
  state = {
    periods: []
  };

  componentDidMount() {}

  render() {
    return (
      <form action="">
        <div className="row">
          <div className="col-3">
            <input
              className="form-control"
              id="period-id"
              name="period_id"
              placeholder="Enter Period ID"
              type="text"
            />
          </div>
          <div className="col-3">
            <input
              className="form-control"
              id="start-time"
              name="start_time"
              placeholder="Enter start time"
              type="text"
            />
          </div>
          <div className="col-3">
          <input
              className="form-control"
              id="end-time"
              name="end_time"
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
    );
  }
}

export default PeriodFilter;
