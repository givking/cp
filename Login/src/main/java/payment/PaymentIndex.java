package payment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/payment")
public class PaymentIndex {
	@RequestMapping("index")
	public ModelAndView Index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("payment/index");
		return mv;
	}
}
