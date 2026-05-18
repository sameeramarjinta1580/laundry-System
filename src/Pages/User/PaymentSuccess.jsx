import { useEffect } from "react";
import axios from "axios";
import { useSearchParams, useNavigate } from "react-router-dom";
import toast from "react-hot-toast";

const PaymentSuccess = () => {
  const [params] = useSearchParams();
  const navigate = useNavigate();

  useEffect(() => {
    const verifyPayment = async () => {
      try {
        const orderId = params.get("order_id");

        if (!orderId) {
          toast.error("Invalid payment");
          return;
        }

        // ✅ CALL BACKEND VERIFY
        await axios.post("http://localhost:8080/payment/verify", {
          orderId,
          status: "success",
        });

        toast.success("Payment verified & booking confirmed 🎉");

        // 👉 redirect after 2 sec
        setTimeout(() => {
          navigate("/my-orders");
        }, 2000);

      } catch (err) {
        console.error(err);
        toast.error("Payment verification failed ❌");
      }
    };

    verifyPayment();
  }, []);

  return (
    <div style={{ textAlign: "center", marginTop: "100px" }}>
      <h1>Processing Payment...</h1>
      <p>Please wait while we confirm your booking.</p>
    </div>
  );
};

export default PaymentSuccess;