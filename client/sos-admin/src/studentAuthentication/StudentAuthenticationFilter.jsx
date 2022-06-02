import React from "react";

class StudentAuthenticationFilter extends React.Component {
  state = {
    provinces_cities: [],
    wards_districts: [],
  };

  componentDidMount() {}

  render() {
    return (
      <form action="">
        <div className="row">
          <div className="col-3">
            <input
              className="form-control"
              id="student-id"
              name="student_id"
              placeholder="Enter Student ID"
              type="text"
            />
          </div>
          <div className="col-3">
            <input
              className="form-control"
              id="student-username"
              name="student_username"
              placeholder="Enter Student Username"
              type="text"
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

export default StudentAuthenticationFilter;
