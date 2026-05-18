import React from "react";

const WashifyLogo = ({ width = 180 }) => {
  return (
    <svg
      width={width}
      viewBox="0 0 400 200"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      {/* Gradient */}
      <defs>
        <linearGradient id="washifyGradient" x1="0" y1="0" x2="1" y2="1">
          <stop offset="0%" stopColor="#2563EB" />
          <stop offset="100%" stopColor="#06B6D4" />
        </linearGradient>
      </defs>

      {/* W Wave */}
      <path
        d="M40 80 
           C60 20, 100 20, 120 80 
           C140 140, 180 140, 200 80
           C220 20, 260 20, 280 80
           C300 140, 340 140, 360 80"
        stroke="url(#washifyGradient)"
        strokeWidth="18"
        fill="none"
        strokeLinecap="round"
      />

      {/* Shine */}
      <circle cx="290" cy="30" r="6" fill="#06B6D4" />
      <circle cx="305" cy="15" r="4" fill="#06B6D4" />

      {/* Text */}
      <text
        x="50%"
        y="170"
        textAnchor="middle"
        fontSize="36"
        fontWeight="600"
        fill="url(#washifyGradient)"
        fontFamily="Poppins, sans-serif"
      >
        Washify
      </text>
    </svg>
  );
};

export default WashifyLogo;