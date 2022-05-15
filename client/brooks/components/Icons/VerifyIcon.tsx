const VerifyIcon = (props: React.HTMLAttributes<SVGElement>) => {
  return (
    <svg
      width="16"
      height="16"
      viewBox="0 0 16 16"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        fillRule="evenodd"
        clipRule="evenodd"
        d="M9.86272 1.33331C9.59579 1.33345 9.33121 1.38318 9.08247 1.47998L8.7757 1.59331C8.52833 1.68601 8.26629 1.73344 8.00212 1.73331C7.73794 1.73344 7.4759 1.68601 7.22853 1.59331L6.92177 1.47998C5.84277 1.06352 4.6265 1.56452 4.1542 2.61998L4.00082 2.91998C3.78079 3.39706 3.39771 3.78001 2.92047 3.99998L2.62037 4.13331C1.56717 4.60796 1.06702 5.82138 1.48001 6.89998L1.59338 7.20664C1.78691 7.70405 1.78691 8.2559 1.59338 8.75331L1.48001 9.05998C1.06702 10.1386 1.56717 11.352 2.62037 11.8266L2.92047 11.96C3.40467 12.1898 3.78867 12.5879 4.00082 13.08L4.1342 13.38C4.6065 14.4354 5.82277 14.9364 6.90176 14.52L7.20853 14.4066C7.70736 14.22 8.25686 14.22 8.75569 14.4066L9.06246 14.52C10.1415 14.9364 11.3577 14.4354 11.83 13.38L11.9634 13.08C12.1933 12.5959 12.5915 12.2121 13.0838 12L13.3839 11.8666C14.4371 11.392 14.9372 10.1786 14.5242 9.09998L14.4109 8.79331C14.2173 8.2959 14.2173 7.74405 14.4109 7.24664L14.5242 6.93998C14.9372 5.86137 14.4371 4.64796 13.3839 4.17331L13.0838 3.99998C12.6065 3.78001 12.2234 3.39706 12.0034 2.91998L11.87 2.61998C11.5179 1.83203 10.7327 1.32706 9.86939 1.33331H9.86272ZM9.86272 2.66664C10.1937 2.66432 10.4946 2.85793 10.6296 3.15998L10.7697 3.46664C11.1242 4.2547 11.7553 4.88558 12.5436 5.23998L12.837 5.36664C13.2497 5.55751 13.4426 6.03652 13.2772 6.45998L13.1705 6.74664C12.8603 7.54653 12.8603 8.43342 13.1705 9.23331L13.2838 9.55998C13.4477 9.9742 13.2568 10.4441 12.8504 10.6266L12.5436 10.7666C11.7553 11.121 11.1242 11.7519 10.7697 12.54L10.643 12.8333C10.5063 13.1378 10.2033 13.3337 9.86939 13.3333C9.76006 13.3315 9.65182 13.3113 9.54928 13.2733L9.26252 13.1666C8.86116 13.0091 8.43331 12.9299 8.00212 12.9333C7.58023 12.9302 7.16158 13.0071 6.76838 13.16L6.44161 13.2733C6.34652 13.3129 6.24453 13.3333 6.14151 13.3333C5.81058 13.3356 5.50961 13.142 5.3746 12.84L5.23455 12.5333C4.88005 11.7452 4.24896 11.1144 3.46065 10.76L3.16722 10.6666C2.7545 10.4758 2.56167 9.99677 2.72708 9.57331L2.83378 9.28664C3.14395 8.48675 3.14395 7.59987 2.83378 6.79998L2.72041 6.47331C2.55649 6.05908 2.74743 5.58923 3.15388 5.40664L3.46065 5.26664C4.25738 4.90546 4.89181 4.26173 5.24122 3.45998L5.36793 3.16664C5.50465 2.8621 5.8076 2.66629 6.14151 2.66664C6.25084 2.66841 6.35908 2.68869 6.46162 2.72664L6.74838 2.83331C7.14766 2.99004 7.57317 3.06923 8.00212 3.06664C8.424 3.06979 8.84265 2.99288 9.23585 2.83998L9.56262 2.72664C9.65771 2.68703 9.7597 2.66664 9.86272 2.66664ZM10.3162 5.56664C10.3149 5.56664 10.3135 5.56664 10.3122 5.56664H10.3162ZM10.3062 5.56673C10.3042 5.56669 10.3022 5.56666 10.3002 5.56664H10.3122C10.3102 5.56666 10.3082 5.56669 10.3062 5.56673ZM10.3062 5.56673C10.3896 5.56873 10.4694 5.60193 10.5296 5.65998L10.883 6.01331C10.9462 6.0759 10.9817 6.1611 10.9817 6.24998C10.9817 6.33886 10.9462 6.42405 10.883 6.48664L7.13517 10.2333C7.07403 10.2967 6.98987 10.3328 6.90176 10.3333C6.81333 10.3344 6.72856 10.2981 6.66835 10.2333L5.10118 8.65331C5.03805 8.59072 5.00255 8.50552 5.00255 8.41664C5.00255 8.32776 5.03805 8.24257 5.10118 8.17998L5.45462 7.82664C5.51513 7.76219 5.59961 7.72563 5.68803 7.72563C5.77645 7.72563 5.86093 7.76219 5.92144 7.82664L6.92177 8.81998L10.0828 5.65998C10.143 5.60193 10.2228 5.56873 10.3062 5.56673Z"
        fill="#636769"
      />
    </svg>
  );
};

export default VerifyIcon;
