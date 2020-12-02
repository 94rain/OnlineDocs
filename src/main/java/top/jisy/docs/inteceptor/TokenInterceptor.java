// package top.jisy.docs.inteceptor;
//
// import com.auth0.jwt.exceptions.AlgorithmMismatchException;
// import com.auth0.jwt.exceptions.SignatureVerificationException;
// import com.auth0.jwt.exceptions.TokenExpiredException;
// import org.springframework.web.servlet.HandlerInterceptor;
//
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
//
// public class JWTInterceptor implements HandlerInterceptor {
//
//     @Override
//     public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//         String token = request.getHeader("token");
//         try {
//             JWTUtils.verify(token);
//         }catch (SignatureVerificationException e){
//             e.printStackTrace();
//             System.out.println("invalid sign");
//             return false;
//         }catch (TokenExpiredException e){
//             e.printStackTrace();
//             System.out.println("token expired");
//             return false;
//         }catch (AlgorithmMismatchException e){
//             e.printStackTrace();
//             System.out.println("token algorithm mismatch");
//             return false;
//         }catch (Exception e){
//             e.printStackTrace();
//             System.out.println("invalid token");
//             return false;
//         }
//         return true;
//     }
// }