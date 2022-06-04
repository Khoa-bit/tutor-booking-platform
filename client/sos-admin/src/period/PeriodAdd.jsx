import React from "react";
import axios from "axios";

class PeriodAdd extends React.Component {
  state = {
    period_id: "",
    tutor_id: "",
    student_id: "",
    start_time: "",
    end_time: "",
    day: "",
  };

  handleChange_period_id = (event) => {
    this.setState({ period_id: event.target.value });
    console.log(this.state);
  };

  handleChange_student_id = (event) => {
    this.setState({ student_id: event.target.value });
    console.log(this.state);
  };

  handleChange_tutor_id = (event) => {
    this.setState({ tutor_id: event.target.value });
    console.log(this.state);
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

  componentDidMount() {}

  handleSubmit = (event) => {
    event.preventDefault();

    axios
      .post("http://localhost:8080/api/add/period", {
        period_id: this.state.period_id,
        tutor_id: this.state.tutor_id,
        student_id: this.state.student_id,
        start_time: this.state.start_time,
        end_time: this.state.end_time,
        day: this.state.day,
      })
      .then((res) => {
        /*const tutors = res.data;
            this.setState({ tutors });*/
      });
  };

  render() {
    return (
      <div className="container mt-5 mb-5">
        <header>
          <h1 className="text-center">Period</h1>

          <div className="d-flex justify-content-center">
            <div className="card mt-5 w-50">
              <div className="card-body">
                <form onSubmit={this.handleSubmit}>
                  <div className="mb-3">
                    <label className="form-label">Tutor ID</label>
                    <input
                      type="text"
                      onChange={this.handleChange_tutor_id}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Student ID</label>
                    <input
                      type="text"
                      onChange={this.handleChange_student_id}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Start time</label>
                    <input
                      type="text"
                      onChange={this.handleChange_start_time}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">End time</label>
                    <input
                      type="text"
                      onChange={this.handleChange_end_time}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Day</label>
                    <input
                      type="text"
                      onChange={this.handleChange_day}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="d-flex justify-content-center">
                    <button type="submit" className="btn btn-success">
                      Create
                    </button>
                    <button
                      type="submit"
                      className="btn btn-outline-primary ms-5"
                    >
                      Cancel
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </header>
      </div>
    );
  }
}

export default PeriodAdd;
