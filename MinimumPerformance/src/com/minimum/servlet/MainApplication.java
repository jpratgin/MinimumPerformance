package com.minimum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimum.utils.Utils;

public class MainApplication extends HttpServlet {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		Utils util = new Utils();

		try {

			// Time when the user click the button.
			Long webTime = Long.valueOf(request.getParameter("webTime"));

			// Times list
			List<Double> times = new ArrayList<Double>();
		

			// Time when
			Long startTime = System.currentTimeMillis();

			// Random words process.
			times.add(new Integer(0), (double) startTime - webTime);
			String filename = util.saveRandomWords(1000);
			long endRandomWords = System.currentTimeMillis();
			times.add(new Integer(1), (double) endRandomWords - startTime);

			// Copy content into 5 files
			String[] files = util.copyContent(filename, 5);
			long endCopyContent = System.currentTimeMillis();
			times.add(new Integer(2), (double) endCopyContent - endRandomWords);

			// Compress content files into zip file
			util.compressContent(files, filename);
			long endCompressContent = System.currentTimeMillis();
			times.add(new Integer(3), (double) endCompressContent - endCopyContent);

			System.out.println("The End");

			int step = 0;

			Double minimum = times.get(0);

			/*List<HashMap<String,Object>> allSteps = new ArrayList<HashMap<String,Object>>();
			
			HashMap<String,Object> resultado = new HashMap<String,Object>();*/

			
			
			System.out.println("Paso " + 0 + " tiempo empleado " + times.get(0));
			

			for (int i = 1; i < times.size(); i++) {

				System.out.println("Paso " + i + " tiempo empleado " + times.get(i));

				if (minimum > times.get(i)) {
					minimum = times.get(i);
					step = i;
				}
			}

			response.setHeader("time", minimum.toString());

			String description = getMinimumStep(step);

			response.setHeader("step", description);

			response.setContentType("text/html");

			showResults(response);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Show a web page with the minimum time.
	 * 
	 * @param response
	 * @throws IOException
	 */
	private void showResults(HttpServletResponse response) throws IOException {
		PrintWriter web = response.getWriter();

		web.println("<html>");
		web.println("<head>");
		web.println("<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>");
		web.println("</head>");
		web.println("<h1>Minimum performance</h1>");
		web.println("<body>");
		web.println("<p><h2>The End</h2></p>");
		web.println("<p>Minimum time :" + response.getHeader("time") + " ms</p>");
		web.println("<p>Step :" + response.getHeader("step") + "</p>");
		web.println("</body>");
		web.println("</html>");

		web.close();

	}

	/**
	 * Get minimum step description.
	 * 
	 * @param times
	 * @return Double
	 */
	private String getMinimumStep(int step) {

		String description = null;
		switch (step) {
		case 0:
			description = "Latency at startup";
			break;
		case 1:
			description = "Generate randow words";
			break;
		case 2:
			description = "Copy content to files";
			break;
		case 3:
			description = "Comprisse content into zip file ";
			break;
		case 4:
			description = "The End ";
			break;
		}
		return description;
	}

}
