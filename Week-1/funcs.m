function funcs
	close all; clear;
	figure;
	hold on;
%	axis ([-1, 1, -1, 1]);

	X = [1:5];
%	Y = a(X)
	plot (X, 2.^(2.^X), 'r');				% 3
	plot (X, 2.^(X.^2), 'b');				% 2
	plot (X, (X.^2).*log2(X), 'g');			% 1
%	plot (X, X, 'k');						% 0
%	plot (X, X.^(2.^X), 'k');				% 4
	
	(5/3)
end;

function [Y] = a(X)
	Y = sqrt (X);
end;

function [dif] = test (T, W)
	m = 10000;
	X = rand(m, 2) * 2 - 1;
	Y = resolve (T, X);
	H = resolve (W, X);
	dif = size(find(Y != H), 1) / m;
end;

function [W, iterations] = perceptron (X, Y)
	n = size(X, 1);
	m = size(X, 2);
	X = [ones(n, 1), X];
	W = zeros(m + 1, 1);
	iterations = 0;
	
	while (1)
		iterations += 1;
		again = 0;
		random = randperm(n);
		
		for i = 1 : n
			r = random(i);
			x = X(r,:);
			y = Y(r);
			h = sign(x * W);
			
			if (h != y)
				W += y * x';
				again = 1;
				break;
			end;
		end;
		
		if (again == 0)
			return;
		end;
	end;
end;

function plotData (X, Y)
	pos = find (Y > 0);
	neg = find (Y < 0);
	zer = find (Y == 0);
	
	plot(X(pos, 1), X(pos, 2), "rx", 'MarkerSize', 3);
	plot(X(neg, 1), X(neg, 2), "b+", 'MarkerSize', 3);
	plot(X(zer, 1), X(zer, 2), "ko", 'MarkerSize', 3);
	
end;

function [Y] = resolve (T, X)
	n = size(X, 1);
	X = [ones(n, 1), X];
	Y = sign(X * T);
end;

function dispThreashold (T, color)
	x1 = -1;
	y1 = -(T(1) + T(2) * x1) / T(3);
	x2 = 1;
	y2 = -(T(1) + T(2) * x2) / T(3);
	
	plot ([x1, x2], [y1, y2], color);
end;
