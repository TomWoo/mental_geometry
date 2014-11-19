I = im2bw(imdilate(imerode(imread('C:/Users/User/SkyDrive/Duke/3_Fall_2014/HackDuke/workspace/mental_geometry/src/images/lvl4.png'), strel('disk',20)), strel('ball',5,5)));

%%# Process Image
%# edge detection
BW = edge(I, 'sobel');
subplot(222), imshow(BW), title('edge')

%# dilation-erosion
se = strel('disk', 2);
BW = imdilate(BW,se);
BW = imerode(BW,se);
subplot(223), imshow(BW), title('dilation-erosion')

%# fill holes
BW = imfill(BW, 'holes');
subplot(224), imshow(BW), title('fill')

%# get boundary
B = bwboundaries(BW, 8, 'noholes');
B = B{1};

%%# boundary signature
%# convert boundary from Cartesian to polar coordinates
objB = bsxfun(@minus, B, mean(B));
[theta, rho] = cart2pol(objB(:,2), objB(:,1));

%# find corners
%#corners = find( diff(diff(rho)>0) < 0 );     %# find peaks
[~,order] = sort(rho, 'descend');
corners = order(1:50);

%# plot boundary signature + corners
figure, plot(theta, rho, '.'), hold on
uniqueCorners = [corners(1)];
threshold = 3;
for i=2:length(corners)
    corner = corners(i);
    for j=1:length(uniqueCorners)
        unique = uniqueCorners(j);
        if abs(rho(corner)-rho(unique))>threshold
            uniqueCorners = [uniqueCorners unique];
            plot(theta(corner), rho(corner), 'ro');
        end
    end
end
hold off
corners = uniqueCorners';
xlim([-pi pi]), title('Boundary Signature'), xlabel('\theta'), ylabel('\rho')

%# plot image + corners
figure, imshow(BW), hold on
plot(B(corners,2), B(corners,1), 's', 'MarkerSize',10, 'MarkerFaceColor','r')
hold off, title('Corners')

% corners = corner(I);
% imshow(I)
% hold on
% plot(corners(:,1),corners(:,2),'r*');

% for i=2:length(corners)-1
%     cornerX = corners(i, 1);
%     cornerY = corners(i, 2);
%     previousX = corners(i-1, 1);
%     previousY = corners(i-1, 2);
%     nextX = corners(i+1, 1);
%     nextY = corners(i+1, 2);
%     
%     previousSlope = (cornerX - previousX)/(cornerY - previousY);
%     nextSlope = (nextX - cornerX) / (nextY - cornerY);
%     if abs(previousSlope-nextSlope)>1
%         plot(corners(:,1),corners(:,2),'go');
%     end
%     disp([previousSlope nextSlope]);
% end
